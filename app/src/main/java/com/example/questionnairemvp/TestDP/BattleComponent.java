package com.example.questionnairemvp.TestDP;

import dagger.Component;


@Component (modules = BravosModule.class)
public interface BattleComponent {
    War getWar();
    Stark getStark();
    Bolton getBolton();
    Cash getCash();
    Soldiers getSoldiers();
}
