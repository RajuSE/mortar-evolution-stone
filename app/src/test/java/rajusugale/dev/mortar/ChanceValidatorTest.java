package rajusugale.dev.mortar;

import org.junit.Before;
import org.junit.Test;

//import static org.fest.assertions.api.Assertions.assertThat;
import rajusugale.dev.mortar.validation.ChanceValidator;
import rajusugale.dev.mortar.validation.Validator;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Raju on 1/23/2017.
 */
public class ChanceValidatorTest {
    public ChanceValidatorTest(){
    }
    ChanceValidator chanceValidator;
    @Before
    public void setUp() throws Exception {
        chanceValidator = new ChanceValidator();
    }

    @Test
    public void t_NO_ERROR() throws Exception {
        int input = 2;
        Validator.ValidationResult validationResult = chanceValidator.validate(input,null);
        assertEquals(validationResult, Validator.ValidationResult.NO_ERROR);
    }
    @Test
    public void t_ERROR_EXCEEDED() throws Exception {
        int input = 7;
        Validator.ValidationResult validationResult = chanceValidator.validate(input,null);
        assertEquals(validationResult, Validator.ValidationResult.ERROR_EXCEEDED);
    }

}