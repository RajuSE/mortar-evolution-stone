/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rajusugale.dev.mortar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rajusugale.dev.mortar.validation.Validator;

public class EvolutionView extends FrameLayout {
  private final EvolutionPresenter evolutionPresenter;

  private TextView textView, tv_instructions;
  private ImageView iv_pokemon;
  private Button btn_isValidChance,btn_enableDisableFlag;
  Context context;

  public EvolutionView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    evolutionPresenter = (EvolutionPresenter) context.getSystemService(EvolutionPresenter.class.getName());
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    textView = (TextView) findViewById(R.id.text);
    tv_instructions = (TextView) findViewById(R.id.tv_instructions);
    iv_pokemon = (ImageView) findViewById(R.id.iv_pokemon);
    btn_isValidChance=(Button)findViewById(R.id.btn_isValidChance);
    btn_enableDisableFlag=(Button)findViewById(R.id.btn_enableDisableFlag);

    btn_isValidChance.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        isValidChance();
      }
    });
    btn_enableDisableFlag.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        enableDisableFlag();
      }
    });
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    evolutionPresenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    evolutionPresenter.dropView(this);
    super.onDetachedFromWindow();
  }

  public void show(CharSequence stuff, String instructions, int resourceId) {
    textView.setText(stuff);
    tv_instructions.setText(instructions);
    iv_pokemon.setImageResource(resourceId);
  }

  public void isValidChance() {
    Validator.ValidationResult validationResult= evolutionPresenter.validation_status();
    if(validationResult== Validator.ValidationResult.NO_ERROR){
      Toast.makeText(context,"You have few chances to Evolve Pokemon!",Toast.LENGTH_SHORT).show();
    }else if(validationResult== Validator.ValidationResult.ERROR_EXCEEDED){
      Toast.makeText(context,"You have used All 2 chances to Evolve! Thanks",Toast.LENGTH_LONG).show();
    }else if(validationResult== Validator.ValidationResult.ERROR_DISABLED){
      Toast.makeText(context,"Evolution is Disabled!! Click Enable!",Toast.LENGTH_LONG).show();
    }
  }

  boolean flag_enable_disable_click=false;
  public void enableDisableFlag() {

    if(flag_enable_disable_click){
      evolutionPresenter.enableDisableFlag(false);//enable
      flag_enable_disable_click=false;
      Toast.makeText(context,"Evolution Enabled!!",Toast.LENGTH_LONG).show();
    }else {
      evolutionPresenter.enableDisableFlag(true);
      flag_enable_disable_click=true;
      Toast.makeText(context,"Evolution Disabled!!",Toast.LENGTH_LONG).show();
    }
  }
}
