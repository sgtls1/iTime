package com.example.itime;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ClockSaver {
    public ClockSaver(Context context) {
        this.context = context;
    }

    Context context;

    public ArrayList<Clock> getBooks() {
        return books;
    }

    ArrayList<Clock> books=new ArrayList<Clock>();
    public static void save(){
        try{
            ObjectOutputStream outputStream=new ObjectOutputStream(
                    context.openFileOutput("Serializable.txt",Context.MODE_PRIVATE)
            );
            outputStream.writeObject(books);
            outputStream.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Clock> load()
    {
        try{
            ObjectInputStream inputStream = new ObjectInputStream(
                    context.openFileInput("Serializable.txt"));
            books= (ArrayList<Clock>) inputStream.readObject();
            inputStream.close();



        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return  books;

    }
}
