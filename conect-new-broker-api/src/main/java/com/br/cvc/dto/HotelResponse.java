package com.br.cvc.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponse implements Serializable {

    private static final long serialVersionUID = 1391068929807200991L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("cityName")
    private String cityName;

    @JsonProperty("rooms")
    private List<RoomResponse> rooms;

    public HotelResponse(int id,String cityName,List<RoomResponse> listRoom){
        this.id = id;
        this.cityName = cityName;
        this.rooms = listRoom;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<RoomResponse> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResponse> rooms) {
        this.rooms = rooms;
    }
}
