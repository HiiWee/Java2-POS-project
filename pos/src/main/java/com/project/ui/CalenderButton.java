package com.project.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

public class CalenderButton extends JPanel{
	CalenderButton(){								//처음 초기값은 현재 날짜이고 그 이후로 위아래 화살표 버튼을 이용하여 날짜 조정 spinner 함수를 활용
		Date now = new Date();
		final SpinnerDateModel model = new SpinnerDateModel(now, null, now, Calendar.DAY_OF_WEEK);
		JSpinner spinner = new JSpinner(model);
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
		JFormattedTextField ftf = editor.getTextField();
		ftf.setEditable(false);
		ftf.setHorizontalAlignment(JTextField.CENTER);
		ftf.setBackground(new Color(255,255,255));
		spinner.setEditor(editor);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Date value = (Date)model.getValue();
				Date next = (Date)model.getNextValue();
				if(value != null && next != null)
					System.out.println("value = " + df.format(value) + "\t" + "next = " + df.format(next));
			}
			
		});
		this.add(spinner);							//문제점은 지금 날짜선택을 한 후 현재 날짜 데이터를 추출해서 저장해야 데이터베이스에 넣기 수월할텐데 아직 어떻게 해야할지 모르겠음..
		
	}
}
