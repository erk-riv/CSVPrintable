package inter;

import java.io.PrintWriter;

public class Teacher implements CSVPrintable {
    protected String name;
    protected int ID;
    protected int phone;

    public Teacher(String name, int ID, int phone) {
        this.name = name;
        this.ID = ID;
        this.phone = phone;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return ID;
    }

    public long getPhone() {
        return phone;
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(getName() + "," + getID() + "," + getPhone());
        out.flush();
    }
}

