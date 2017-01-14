package rajusugale.dev.mortar;

import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mortar.ViewPresenter;

class EvolutionPresenter extends ViewPresenter<EvolutionView> {
  private final DateFormat format = new SimpleDateFormat();
  private int serial = 0;
  private int resourceId;
  private String instructions="";

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    if (savedInstanceState != null && serial == 0)
      serial = savedInstanceState.getInt("serial");

    instructions=getView().getResources().getString(R.string.instr);

    ++serial;
    if(serial>3){//As Bulbasaur Has 3 Evolutions  Only :)
      serial = 1;
      instructions=getView().getResources().getString(R.string.instr);
    }
    if(serial==1){
      resourceId=R.drawable.bulba_1;
    }else if(serial==2){
      resourceId=R.drawable.ivy_2;
    }else if(serial==3){
      resourceId=R.drawable.ven_3;
      instructions="Rotate and Mortar will get Bulbasaur back!";
    }

    getView().show("Evolution #" + serial + " at " + format.format(new Date()), instructions, resourceId);
  }

  @Override
  protected void onSave(Bundle outState) {
    outState.putInt("serial", serial);
  }
}
