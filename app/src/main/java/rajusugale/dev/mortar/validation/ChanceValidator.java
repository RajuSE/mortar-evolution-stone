package rajusugale.dev.mortar.validation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.io.Serializable;


public class ChanceValidator implements Validator, Serializable {


  @Override
  public void reportFlag(SharedPreferences sharedPreferences, boolean flag_enable_disable) {
    SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.putBoolean("flag_disable",flag_enable_disable);
    editor.apply();
  }

  @Override public ValidationResult validate(int input, SharedPreferences sharedPref) {//create zeroable annotation TODO

    if (sharedPref== null) {  //For UnitTest

      return actualValidation(input);
    } else {                  //For Instrumentation mockito Test
      boolean flag_disable = sharedPref.getBoolean("flag_disable", false);
      if (flag_disable)
        return ValidationResult.ERROR_DISABLED;

      return actualValidation(input);
    }
  }

  ValidationResult actualValidation(int input){
    if (input > 6) return ValidationResult.ERROR_EXCEEDED;
    return ValidationResult.NO_ERROR;
  }
}
