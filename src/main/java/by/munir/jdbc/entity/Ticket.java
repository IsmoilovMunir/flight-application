package by.munir.jdbc.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
   private Long id;
   private String passportNo;
   private String passengerName;
   private Long flightId;
   private String seatNo;
   private BigDecimal cast;

    public Ticket() {
    }

    public Ticket(BigDecimal cast, Long flightId, Long id, String passengerName, String passportNo, String seatNo) {
        this.cast = cast;
        this.flightId = flightId;
        this.id = id;
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.seatNo = seatNo;
    }

    public BigDecimal getCast() {
        return cast;
    }

    public void setCast(BigDecimal cast) {
        this.cast = cast;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "cast=" + cast +
               ", flightId=" + flightId +
               ", id=" + id +
               ", passengerName='" + passengerName + '\'' +
               ", passportNo='" + passportNo + '\'' +
               ", seatNo='" + seatNo + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(passportNo, ticket.passportNo) && Objects.equals(passengerName, ticket.passengerName) && Objects.equals(flightId, ticket.flightId) && Objects.equals(seatNo, ticket.seatNo) && Objects.equals(cast, ticket.cast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportNo, passengerName, flightId, seatNo, cast);
    }
}
