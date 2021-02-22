package com.verificationapplication.poc.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class VerificationOutput {

    private boolean allPlayersValid;
    private List<FailedVerificationReasons> verificationIssues = new ArrayList<>();

    public VerificationOutput() {
    }

    public boolean isAllPlayersValid() {
        return allPlayersValid;
    }

    public void setAllPlayersValid(boolean allPlayersValid) {
        this.allPlayersValid = allPlayersValid;
    }

    public List<FailedVerificationReasons> getVerificationIssues() {
        return verificationIssues;
    }

    public void setVerificationIssues(FailedVerificationReasons verificationIssues) {
        this.verificationIssues.add(verificationIssues);
    }
}
