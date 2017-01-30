package rajusugale.dev.mortar.validation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public interface Validator {
    void reportFlag(SharedPreferences sharedPreferences, boolean flag_enable_disable);

    enum ValidationResult {
        /**
         * The input is valid
         */
       NO_ERROR ,ERROR_EXCEEDED, ERROR_DISABLED;

    }

    ValidationResult validate(int input, SharedPreferences sharedPref);
}