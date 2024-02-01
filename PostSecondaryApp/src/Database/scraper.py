from bs4 import BeautifulSoup
import requests
import re
import json
from time import sleep
from tqdm import tqdm

TESTING = False

ROOT = "https://www.ontariouniversitiesinfo.ca/"


def clean(txt, invalid_substrs, sub):

    txt = str(txt)
    
    
    for substr in invalid_substrs:
        txt = re.sub(substr, sub, txt)
    return txt


def get_program_links():
    
    letters = ["a", "b", "c", "d-e", "f-g", "h", "i", "j-l", "m", "n-p", "q-s", "t-z"]
    
    if(TESTING):
        letters = ["t-z"]
    
    program_list_url = "programs/search/?search=&group="

    program_links = []

    for letter in letters:
        
        result = requests.get(ROOT+program_list_url+letter);
        doc = BeautifulSoup(result.text, "html.parser")

        program_entries = doc.find_all("h2", attrs={'class':'result-heading'})

        for element in program_entries:
            element = str(element)
            ind = element.find("href=")
            ind_start = ind+7
            ind_end = -1
            for i in range(ind_start, len(element)):
                if(element[i]=="\""):
                    ind_end=i
                    break
                
            program_links.append(ROOT+element[ind_start:ind_end])
            
    return program_links
    
programs = []
    
def remove_tag(string, tagl, tagr):
    string = str(string)
    
    for i in range(0, len(string)-len(tagl)):
        if(string[i:i+len(tagl)]==tagl):
            for j in range(i, len(string)-len(tagr)):
                if(string[j:j+len(tagr)]==tagr):
                    string = string[0:i]+string[j+len(tagr):len(string)]
                    break
    
    return string
    
def parse_program(program_link):
    result = requests.get(program_link)
    doc = BeautifulSoup(result.text, "html.parser")
    
    
    # table
    invalid_substrs = ['<dt>', '<dd>', '<span>', '<p>', '</dt>', '</dd>', '</span>', '</p>', "\\n", "\\t"]
    
    keys = [clean(text, invalid_substrs, '') for text in doc.find_all("dt")]
    values = [clean(text, invalid_substrs, '') for text in doc.find_all("dd")]
    
    if(len(keys)==7):
        keys.append("Notes")
        values.append("N/A")
    notes = values[-1]
    notes = clean(notes, ['<ul>','</br>','</ul>', '\\r'], '')
    notes = clean(notes, ['</li>'], ', ')
    notes = clean(notes, ['<li>', '<br>'], ' ')
    values[-1] = remove_tag(notes, '<a', '</a>')
    
    table = list(zip(keys, values))
    
    
    # prerequisities
    prerequisites_div2 = doc.find_all(string="Prerequisites")[0].parent.parent
    
    prerequisites_div = re.sub("\n", "", str(prerequisites_div2))

    prerequisites = []
    
    point_form=False
    for i in range(0, len(prerequisites_div)):
        if i+3<len(prerequisites_div) and prerequisites_div[i:i+4]=="<li>":
            point_form=True
            for j in range(i+4, len(prerequisites_div)):
                if j+4<len(prerequisites_div) and prerequisites_div[j:j+4]=="<li>":
                    break
                if j+4<len(prerequisites_div) and prerequisites_div[j:j+5]=="</li>":
                    prerequisite = prerequisites_div[i+4:j]
                    prerequisite = clean(prerequisite, ["\\r", "\\t", "<ul>", "<li>", "<strong>", "</ul>", "</li>", "</strong>"], '')
                    prerequisite = clean(prerequisite, ["\\xa0"], ' ')
                    prerequisite = clean(prerequisite, ["&amp;"], '&')
                    prerequisites.append(prerequisite)
                    break
    
    if not point_form:
        prerequisites.append(''.join(prerequisites_div2.text.split('\n')[2:-1]))
                    
    # contact info                    
    contact_div = doc.find_all(string="Contact Us About the Program")[0].parent.parent

    contact_div = list(contact_div)

    address = clean(contact_div[3].find("p").text, ['\\n', '\\r'], ' ')
    
    inquiry_email = "N/A"
    
    try :
        inquiry_email = contact_div[3].find("a").get("href")[7:]
    except:
        inquiry_email = "N/A"
        
        
    program = {}
        
    for i in table:
        program[i[0]]=i[1]
        
    program['prerequisites']=prerequisites
    program['address']=address
    program['email']=inquiry_email
    program['link']=program_link
    
    programs.append(program)
    
    
def main():
    program_links = get_program_links()
    
    for i in tqdm(range(0, len(program_links))):
        parse_program(program_links[i])
        
    json_file = json.dumps(programs, indent=2, ensure_ascii=False)

    with open('programs.json', 'w', encoding='utf-8') as f:
        f.write(json_file)
    
main()