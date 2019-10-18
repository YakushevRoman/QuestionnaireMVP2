package com.example.questionnairemvp.TestDP;

import android.util.Log;

import com.example.questionnairemvp.Constants.Constants;

import javax.inject.Inject;

public class Bolton implements House {

    @Inject
    Bolton() {
    }

    @Override
    public void prepareForWar() {
        Log.d(Constants.ConstantsGlobal.TAG, String.format("Prepeare for war : %S ",this.getClass().getName()));

    }

    @Override
    public void reportForWar() {
        Log.d(Constants.ConstantsGlobal.TAG, String.format("Report For War : %S ",this.getClass().getName()));

    }
}
