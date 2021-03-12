package com.br.cvc.service;


import com.br.cvc.client.BrokerClient;
import com.br.cvc.dto.Hotel;
import com.br.cvc.dto.HotelResponse;
import com.br.cvc.dto.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HotelBrokerService {

    private static final Logger LOG = Logger.getLogger(HotelBrokerService.class.getName());

    private static final BigDecimal COMMISSION = new BigDecimal(0.7);

    @Autowired
    private BrokerClient clientApi;

    public HotelResponse findHotelById( int id_hotel) throws Exception {

    	
    	LOG.info("Find Hotel api externa: " + id_hotel);
    	
        Hotel hotel =  this.clientApi.findHotel(id_hotel);

        List<RoomResponse> listRoom = hotel.getRooms().parallelStream().map(room ->{

        	final int dia = 1;
        	final int adult = 1;
        	final int child = 1;
            BigDecimal calpricePerDayAdult = calculateDailyCommission(dia,adult,room.getPrice().getAdult(),COMMISSION);
            BigDecimal calpricePerDayChild = calculateDailyCommission(dia,child,room.getPrice().getChild(),COMMISSION);
            BigDecimal total = calpricePerDayAdult.add(calpricePerDayChild);

            return new RoomResponse(room.getRoomID(),room.getCategoryName(),calpricePerDayAdult,
                    calpricePerDayChild,total);
        }).collect(Collectors.toList());

        return  new HotelResponse(hotel.getId(),hotel.getCityName(),listRoom);
    }


    public List<HotelResponse> findListHotel(int dias, int qtd_adulto, int qtd_crianca, int id_cidade) throws Exception {

    	LOG.info("Find Array Hotel api externa: " + id_cidade);
        Hotel[] hoteis =  this.clientApi.findHotelByCity(id_cidade);
        List<HotelResponse> lista = Arrays.stream(hoteis).map(hotel->{
            List<RoomResponse> listRoom = hotel.getRooms().parallelStream().map(room ->{

                BigDecimal calpricePerDayAdult = calculateDailyCommission(dias,qtd_adulto,room.getPrice().getAdult(),COMMISSION);
                BigDecimal calpricePerDayChild = calculateDailyCommission(dias,qtd_crianca,room.getPrice().getChild(),COMMISSION);
                BigDecimal total = calpricePerDayAdult.add(calpricePerDayChild);

                return new RoomResponse(room.getRoomID(),room.getCategoryName(),calpricePerDayAdult,
                        calpricePerDayChild,total);
            }).collect(Collectors.toList());

            return  new HotelResponse(hotel.getId(),hotel.getCityName(),listRoom);
        }).collect(Collectors.toList());

        return lista;
    }

    public BigDecimal calculateDailyCommission(int dias , int qtd_pessoa ,BigDecimal valor, BigDecimal comissao){
        BigDecimal  dValor = ((valor.multiply(new BigDecimal(dias))).multiply(new BigDecimal(qtd_pessoa))).divide(comissao,2, RoundingMode.HALF_UP);
        return dValor;
    }
}
