package info.androidhive.slidingmenu.listener;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.TodoListFragment;
import info.androidhive.slidingmenu.adapter.TaskItemListAdapter;
import info.androidhive.slidingmenu.model.TaskItem;

/**
 * Created by fukuokak on 2014/10/24.
 */
public class ToDoListFragmentButtonListener implements View.OnClickListener {
    private Activity activity;

    public ToDoListFragmentButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                onClickAddButton(v);
                break;
            default:
                break;
        }
        ;
    }

    public void onClickAddButton(View v) {
        saveTaskItem();
        ListView toDoList = (ListView) activity.findViewById(R.id.toDoListView);


        TaskItemListAdapter todoAdapter = new TaskItemListAdapter(activity.getApplicationContext(),
                getTaskItem());

        toDoList.setAdapter(todoAdapter);
    }

    public ArrayList<TaskItem> getTaskItem() {
        ArrayList<TaskItem> taskItemArrayList = new ArrayList<TaskItem>();
        TaskItem ti1 = new TaskItem("1/1", 1, "睡眠", TaskItem.TASK_INTERVAL_DAILY, "00:00");
        TaskItem ti2 = new TaskItem("1/1", 2, "ご挨拶", TaskItem.TASK_INTERVAL_YEARLY, "01:00");
        taskItemArrayList.add(ti1);
        taskItemArrayList.add(ti2);
        return taskItemArrayList;
    }

    public void saveTaskItem() {


        try {
            OutputStream out = activity.openFileOutput("a.txt", Activity.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            //Todo : 読める形に保管する必要がある。
            for (int i = 0; i < getTaskItem().size(); i++) {
                TaskItem ti = getTaskItem().get(i);
                String s = ti.getScheduleDate() + ","
                        + ti.getTaskNum() + ","
                        + ti.getTaskTitle() + ","
                        + ti.getDoTime() + ","
                        + ti.getTaskInterval();
                writer.append(s);

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
