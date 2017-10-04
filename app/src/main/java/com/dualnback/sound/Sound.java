package com.dualnback.sound;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import com.dualnback.R;

import java.util.ArrayList;
import java.util.List;


public enum Sound {

    ASound( Resources.getSystem().getInteger( R.raw.a ) ),
    BSound( Resources.getSystem().getInteger( R.raw.b ) ),
    CSound( Resources.getSystem().getInteger( R.raw.c ) ),
    DSound( Resources.getSystem().getInteger( R.raw.d ) ),
    ESound( Resources.getSystem().getInteger( R.raw.e ) ),
    FSound( Resources.getSystem().getInteger( R.raw.f ) ),
    GSound( Resources.getSystem().getInteger( R.raw.g ) ),
    HSound( Resources.getSystem().getInteger( R.raw.h ) ),
    ISound( Resources.getSystem().getInteger( R.raw.i ) ),
    JSound( Resources.getSystem().getInteger( R.raw.j ) ),
    KSound( Resources.getSystem().getInteger( R.raw.k ) ),
    LSound( Resources.getSystem().getInteger( R.raw.l ) ),
    MSound( Resources.getSystem().getInteger( R.raw.m ) ),
    NSound( Resources.getSystem().getInteger( R.raw.n ) ),
    OSound( Resources.getSystem().getInteger( R.raw.o ) ),
    PSound( Resources.getSystem().getInteger( R.raw.p ) ),
    QSound( Resources.getSystem().getInteger( R.raw.q ) ),
    RSound( Resources.getSystem().getInteger( R.raw.r ) ),
    SSound( Resources.getSystem().getInteger( R.raw.s ) ),
    TSound( Resources.getSystem().getInteger( R.raw.t ) ),
    USound( Resources.getSystem().getInteger( R.raw.u ) ),
    VSound( Resources.getSystem().getInteger( R.raw.v ) ),
    WSound( Resources.getSystem().getInteger( R.raw.w ) ),
    XSound( Resources.getSystem().getInteger( R.raw.x ) ),
    YSound( Resources.getSystem().getInteger( R.raw.y ) ),
    ZSound( Resources.getSystem().getInteger( R.raw.z ) );

    int i;

    Sound( int integer ) {
        i = integer;
    }

    public static List<MediaPlayer> soundPlayerCollection( Context context ) {

        List<MediaPlayer> allSounds = new ArrayList<>();

        for ( Sound sound : Sound.values() ) {
            allSounds.add( MediaPlayer.create( context, sound.i ) );
        }

        return allSounds;
    }
}
