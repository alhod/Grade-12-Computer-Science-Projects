package Model;

import Controller.*;
import View.*;

import java.util.*;

public class OfferArea {

    int num_offer_slots;

    ArrayList<OfferSlot> offerSlots;

    public OfferArea() {
        setNum_offer_slots(3);

        setOfferSlots(new ArrayList<OfferSlot>());

        for(int i=0; i<getNum_offer_slots(); i++){
            getOfferSlots().add(new OfferSlot());
        }
    }

    public int getNum_offer_slots() {
        return num_offer_slots;
    }

    public void setNum_offer_slots(int num_offer_slots) {
        this.num_offer_slots = num_offer_slots;
    }

    public ArrayList<OfferSlot> getOfferSlots() {
        return offerSlots;
    }

    public void setOfferSlots(ArrayList<OfferSlot> offerSlots) {
        this.offerSlots = offerSlots;
    }

    
    
}
