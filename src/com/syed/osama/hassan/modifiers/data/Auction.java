package com.syed.osama.hassan.modifiers.data;

import java.io.Serializable;
import java.util.*;

public class Auction implements Serializable {
    private final List<Bid> bids = new ArrayList<>();

    private transient volatile boolean isAuctionedStarted;

    public synchronized void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public synchronized List<Bid> getAllBids() {
        return Collections.unmodifiableList(bids);
    }

    public synchronized Optional<Bid> getHighestBid() {
        return bids.stream()
                .max(Comparator.comparing(Bid::getPrice));
    }

    public void startAuction() {
        this.isAuctionedStarted = true;
    }

    public void stopAuction() {
        this.isAuctionedStarted = false;
    }

    public boolean isAuctionRunning() {
        return this.isAuctionedStarted;
    }
}
