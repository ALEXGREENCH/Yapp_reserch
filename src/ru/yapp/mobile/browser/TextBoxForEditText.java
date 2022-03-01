package ru.yapp.mobile.browser;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.gui.EditText;

public final class TextBoxForEditText extends TextBox implements CommandListener {
   private Command cmdCancel = new Command("Отмена", 3, 0);
   private Command cmdOk = new Command("Ок", 4, 0);
   private EditText editText;

   public TextBoxForEditText(EditText editText) {
      super("Введите значение", "", 256, 0);
      this.editText = editText;
      if (editText.text != null) {
         this.setString(editText.text);
      } else {
         this.setString("");
      }

      int constraints = TextField.ANY;
      if (editText.isPassword) {
         constraints = 0 | TextField.PASSWORD;
      }

      if (editText.isNumeric) {
         constraints |= TextField.NUMERIC;
      }

      this.setConstraints(constraints);
      this.addCommand(this.cmdOk);
      this.addCommand(this.cmdCancel);
      this.setCommandListener(this);
      Yapp.display.setCurrent(this);
   }

   public final void commandAction(Command command, Displayable displayable) {
      if (command.equals(this.cmdOk)) {
         this.editText.text = this.getString();
      } else if (command.equals(this.cmdCancel)) {
         this.setString("");
      }

      Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
      BrowserForm.prepareRender = true;
   }
}
