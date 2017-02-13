package rajusugale.dev.mortar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.io.FileOutputStream;

import javax.inject.Inject;

import rajusugale.dev.mortar.validation.ChanceValidator;
import rajusugale.dev.mortar.validation.Validator;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EvolutionInstrumTest {

    @Mock
    Context context;

    @Mock
    SharedPreferences sharedPreferences;

    @Mock
    FileOutputStream fileOutputStream;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("rajusugale.dev.codelib", appContext.getPackageName());
    }

    @Test
    public void t_ERROR_DISABLED()  {//passes
        ChanceValidator chanceValidator  = new ChanceValidator();

        Mockito.when(sharedPreferences.getBoolean(anyString(), anyBoolean())).thenReturn(true);//mock flag as true
        Validator.ValidationResult result = chanceValidator.validate(2, sharedPreferences);
        assertEquals(result, Validator.ValidationResult.ERROR_DISABLED);
    }

    @Test
    public void t_NO_ERROR()  {//passes
        ChanceValidator chanceValidator  = new ChanceValidator();
        Validator.ValidationResult result = chanceValidator.validate(2, sharedPreferences);
        assertEquals(result, Validator.ValidationResult.NO_ERROR);
    }

    @Test
    public void t_ERROR_EXCEEDED()  {//passes
        ChanceValidator chanceValidator  = new ChanceValidator();
        Validator.ValidationResult result = chanceValidator.validate(7, sharedPreferences);
        assertEquals(result, Validator.ValidationResult.ERROR_EXCEEDED);
    }
}
