package com.example.questionnairemvp.TestDP;

import dagger.Module;
import dagger.Provides;

@Module
public class BravosModule {
    Cash cash;
    Soldiers soldiers;

    public BravosModule(Cash cash, Soldiers soldiers) {
        this.cash = cash;
        this.soldiers = soldiers;
    }

    @Provides
    Cash provideCash(){
        return cash;
    }

    @Provides
    Soldiers provideSoldiers(){
        return soldiers;
    }
}
