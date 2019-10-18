package com.example.questionnairemvp.TestDP;

import javax.inject.Inject;

import dagger.Module;

public class War {

    private Stark stark;
    private Bolton bolton;

    @Inject
    War(Stark stark, Bolton bolton) {
        this.stark = stark;
        this.bolton = bolton;
    }

    public void prepare (){
        stark.prepareForWar();
        bolton.prepareForWar();
    }

    public void report (){
        stark.reportForWar();
        bolton.reportForWar();
    }
}
