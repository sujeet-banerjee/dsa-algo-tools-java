
interface TicketCounter {
    public void open();
    public void close();
    public String bookTicket(String name);
}
class MyTicketCounter implements TicketCounter{
    
    public static int ticketId = 0;
    
    public void open() {
        System.out.println("Counter Now Open!!!");
    }
    public void close()  {
        System.out.println("Counter is Closed!");
    }
    public String bookTicket(String name) {
        return "Ticket-"+(ticketId++);
    }
    
}

public class Test {
    public static void main(String[] args) {
        TicketCounter myTC = new MyTicketCounter();
        myTC.open();
        String ticket1 = myTc.bookTicket("Matrix");
        String tickect2 = myTc.bookTicket("Pushpa");
        System.out.println(String.format("Booked tickets: %s, %s", ticket1, ticket2));
        myTC.close();
    }
}