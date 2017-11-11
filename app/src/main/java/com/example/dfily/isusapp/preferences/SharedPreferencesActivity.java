package com.example.dfily.isusapp.preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfily.isusapp.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        /*

        Настройки из одного файла
        SharedPreferences preferences = getPreferences(0);

        Настройки из конкретного файла
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        Второй параметр - режим доступа

        MODE_PRIVATE = 0 Доступ к настройкам есть только у нашего приложения
        MODE_WORLD_READABLE = 1 Не используется с АПИ 17, дает доступ для чтения настроек другим приложениям
        MODE_WORLD_WRITEABLE = 2 Не используется с АПИ 17, дает доступ для записи настроек другим приложениям
        MODE_MULTI_PROCESS = 4 Не используется с АПИ 23, дает возможность записывать данные в файл из разных процессов

         */

    }

    public void saveValue(View view) {
        // Получаем настройки
        SharedPreferences preferences = getPreferences(0);
        // Редактор настроек
        SharedPreferences.Editor editor = preferences.edit();

        EditText editText = (EditText) findViewById(R.id.editText);
        int value = Integer.valueOf(editText.getText().toString());
        // Записываем значение
        editor.putInt("value", value);
        // Сохраняем настройки
        editor.apply();

        Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show();
    }

    public void loadValue(View view) {
        SharedPreferences sharedPreferences = getPreferences(0);
        // Заружаем параметр. Если его не существует, то по умолчанию берется 1
        int value = sharedPreferences.getInt("value", 0);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(String.valueOf(value));
    }
}
