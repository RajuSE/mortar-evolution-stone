package rajusugale.dev.mortar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mortar.ViewPresenter;
import rajusugale.dev.mortar.validation.ChanceValidator;
import rajusugale.dev.mortar.validation.Validator;

class EvolutionPresenter extends ViewPresenter<EvolutionView> {
  private final DateFormat format = new SimpleDateFormat();
  private int serial = 0;
  private int flag_two_chance=0;
  private int resourceId;
  private String instructions="";
  private ChanceValidator chanceValidator;
  SharedPreferences sharedPreferences;

  @Override
  protected void onLoad(Bundle savedInstanceState) {
    if (savedInstanceState != null && serial == 0) {
      serial = savedInstanceState.getInt("serial");
      flag_two_chance = savedInstanceState.getInt("flag_two_chance");
      chanceValidator = (ChanceValidator) savedInstanceState.getSerializable("chanceValidator");
    }else{
      ++serial;
      chanceValidator=new ChanceValidator();
    }

    sharedPreferences = getView().getContext().getSharedPreferences("RepoPrefs", Context.MODE_PRIVATE);

    instructions=getView().getResources().getString(R.string.instr);

    Validator.ValidationResult validationResult=validation_status();

    if(validationResult== Validator.ValidationResult.NO_ERROR){//As Bulbasaur Has 3 Evolutions  Only :)
      instructions=getView().getResources().getString(R.string.instr);
    }else if(validationResult== Validator.ValidationResult.ERROR_EXCEEDED){
      serial = 1;
      instructions=getView().getResources().getString(R.string.instr_exceeded);
      if(flag_two_chance >= 2){
        instructions=getView().getResources().getString(R.string.instr_exceeded);
        enableDisableFlag(true);
      }
      flag_two_chance++;
    }else  if(validationResult== Validator.ValidationResult.ERROR_DISABLED){
      serial = 1;
      instructions=getView().getResources().getString(R.string.instr_disabled);
    }
    if(serial==1 || serial ==4){
      resourceId=R.drawable.bulba_1;
    }else if(serial==2|| serial ==5){
      resourceId=R.drawable.ivy_2;
    }else if(serial==3 ||serial >=6){
      resourceId=R.drawable.ven_3;
      instructions="Rotate and Mortar will get Bulbasaur back!";
      if(flag_two_chance > 2){
        instructions=getView().getResources().getString(R.string.instr_exceeded);
        enableDisableFlag(true);
      }
      flag_two_chance++;
    }

    int evolNum=serial;
    if(serial>3){
      evolNum=serial-3;
    }
    getView().show("Evolution #" + evolNum + " at " + format.format(new Date()), instructions, resourceId);
  }

  public Validator.ValidationResult validation_status() {
    return (chanceValidator.validate(serial, sharedPreferences));
  }

  public void enableDisableFlag(boolean flag){
    chanceValidator.reportFlag(sharedPreferences, flag);
  }

  @Override
  protected void onSave(Bundle outState) {
    outState.putInt("serial", serial);
    outState.putInt("flag_two_chance", flag_two_chance);
    outState.putSerializable("chanceValidator", chanceValidator);
  }
}
