package ru.yapp.mobile.browser;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;

import ru.yapp.mobile.ScreenCanvas;
import ru.yapp.mobile.Yapp;
import ru.yapp.mobile.gui.SelectableFormView;

public final class FormForSelectableView extends Form implements CommandListener {
    private Command cmdCancel = new Command("Отмена", 3, 0);
    private Command cmdOk = new Command("Ок", 4, 0);
    private SelectableFormView selectableFormView;
    private ChoiceGroup d = new ChoiceGroup("значения", 1);

    public FormForSelectableView(SelectableFormView selectableFormView) {
        super("Выберите значение");
        this.append(this.d);
        this.selectableFormView = selectableFormView;

        for (int i = 0; i < selectableFormView.vars.length; ++i) {
            this.d.append(selectableFormView.vars[i], (Image) null);
            if (selectableFormView.text.equals(this.d.getString(i))) {
                this.d.setSelectedIndex(i, true);
            }
        }

        this.addCommand(this.cmdOk);
        this.addCommand(this.cmdCancel);
        this.setCommandListener(this);
        Yapp.display.setCurrent(this);
    }

    public final void commandAction(Command cmd, Displayable disp) {
        if (cmd.equals(this.cmdOk)) {
            this.selectableFormView.text = this.d.getString(this.d.getSelectedIndex());
        } else {
            cmd.equals(this.cmdCancel);
        }

        Yapp.display.setCurrent(ScreenCanvas.screenCanvas);
        BrowserForm.prepareRender = true;
    }
}
