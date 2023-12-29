package travelagency;
/**
 *
 * @author Byron
 */
public class Traveler {
    private String ticketId;
    private String issueDate;
    private String clientName;
    private String itinerary;
    private double cost;
    private String seatNum;
    private String flightId;
    private String departureTime;
    private String arrivalTime;

    public Traveler(String ticketId, String issueDate, String clientName, String itinerary, double cost, String seatNum, String flightId, String departureTime, String arrivalTime) {
        this.ticketId = ticketId;
        this.issueDate = issueDate;
        this.clientName = clientName;
        this.itinerary = itinerary;
        this.cost = cost;
        this.seatNum = seatNum;
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    public Traveler(){
        
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    
    @Override
    public String toString() {
        return ticketId + "\t" + issueDate + "\t" + clientName + "\t" + itinerary + "\t" + cost + "\t" + seatNum + "\t" + flightId + "\t" + departureTime + "\t" + arrivalTime;
    }
    
    
    
}
