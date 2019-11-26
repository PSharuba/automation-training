package by.mmf.sharubapv.model;

import java.util.Objects;

public class SearchQuery {
    private String city;
    private String dateToEnterHotel;
    private String dateToLeaveHotel;

    public SearchQuery(String city, String dateToEnterHotel, String dateToLeaveHotel) {
        this.city = city;
        this.dateToEnterHotel = dateToEnterHotel;
        this.dateToLeaveHotel = dateToLeaveHotel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateToEnterHotel() {
        return dateToEnterHotel;
    }

    public void setDateToEnterHotel(String dateToEnterHotel) {
        this.dateToEnterHotel = dateToEnterHotel;
    }

    public String getDateToLeaveHotel() {
        return dateToLeaveHotel;
    }

    public void setDateToLeaveHotel(String dateToLeaveHotel) {
        this.dateToLeaveHotel = dateToLeaveHotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchQuery that = (SearchQuery) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(dateToEnterHotel, that.dateToEnterHotel) &&
                Objects.equals(dateToLeaveHotel, that.dateToLeaveHotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, dateToEnterHotel, dateToLeaveHotel);
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "city='" + city + '\'' +
                ", dateToEnterHotel='" + dateToEnterHotel + '\'' +
                ", dateToLeaveHotel='" + dateToLeaveHotel + '\'' +
                '}';
    }
}
