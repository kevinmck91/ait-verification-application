package com.verificationapplication.poc.dataobjects;

public class PlayerInputData {
    private int membershipId;
    private int clubId;
    private int ageGroup;

    public PlayerInputData(){}

    public PlayerInputData(int membershipId, int clubId, int ageGroup) {
        this.membershipId = membershipId;
        this.clubId = clubId;
        this.ageGroup = ageGroup;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(int ageGroup) {
        this.ageGroup = ageGroup;
    }

    @Override
    public String toString() {
        return "PlayerInputData{" +
                "membershipId=" + membershipId +
                ", clubId=" + clubId +
                ", ageGroup=" + ageGroup +
                '}';
    }
}
