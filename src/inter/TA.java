package inter;

import java.io.PrintWriter;

public class TA extends Student {
    protected int TID;

    public TA(String name, int ID, int TID, long phone) {
        super(name, ID, phone);
        this.TID = TID;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getID() {
            if (TID > ID)
                return TID;
            else
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

