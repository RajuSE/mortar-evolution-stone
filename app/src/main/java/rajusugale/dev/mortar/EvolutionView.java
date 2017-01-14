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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EvolutionView extends FrameLayout {
  private final EvolutionPresenter presenter;

  private TextView textView, tv_instructions;
  private ImageView iv_pokemon;

  public EvolutionView(Context context, AttributeSet attrs) {
    super(context, attrs);
    presenter = (EvolutionPresenter) context.getSystemService(EvolutionPresenter.class.getName());
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    textView = (TextView) findViewById(R.id.text);
    tv_instructions = (TextView) findViewById(R.id.tv_instructions);
    iv_pokemon = (ImageView) findViewById(R.id.iv_pokemon);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    presenter.dropView(this);
    super.onDetachedFromWindow();
  }

  public void show(CharSequence stuff, String instructions, int resourceId) {
    textView.setText(stuff);
    tv_instructions.setText(instructions);
    iv_pokemon.setImageResource(resourceId);
  }
}
