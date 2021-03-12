package com.br.cvc.controller;


import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import com.br.cvc.exception.ErroCampo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cvc.dto.HotelResponse;
import com.br.cvc.service.HotelBrokerService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/hotel-broker")
@Validated
public class HotelBrokerController {

    private static final Logger LOG = Logger.getLogger(HotelBrokerController.class.getName());

    @Autowired
    private HotelBrokerService hotelBrokerService;


    
    @ApiOperation(value = "Buscar lista de Hotel")
    @GetMapping("/consultar-hotel/{hotelId}")
    public ResponseEntity<HotelResponse>  findHotelById (@PathVariable(name = "hotelId",required = true) String id_hotel) throws Exception {
        
    	LOG.info("findHotelById: " + id_hotel);
        
        HotelResponse response = this.hotelBrokerService.findHotelById(Integer.parseInt(id_hotel));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/consultar-disponibilidade/")
    public ResponseEntity<?> findListHotel (@RequestParam Long cityCode, 
			@RequestParam String checkin,
            @RequestParam String checkout, 
            @RequestParam Long qtd_adult,
            @RequestParam Long qtd_child) throws Exception {

        int qtd_dias = 1;

        try{
            qtd_dias = this.daysBetwwenTwoDate(checkin,checkout);

        } catch( Exception ex) {
            ErroCampo erro = new ErroCampo("Data",ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
        
        List<HotelResponse> response = this.hotelBrokerService.findListHotel(qtd_dias,qtd_adult.intValue(),
        		qtd_child.intValue(),cityCode.intValue());
        return new ResponseEntity<>(response,HttpStatus.OK );

    }

    private int daysBetwwenTwoDate(String sDateBefore,String sDateAfter) throws Exception {
    	
    	int qtd = 1;


    	try{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dateBefore = LocalDate.parse(sDateBefore,formato);
            LocalDate dateAfter = LocalDate.parse(sDateAfter,formato);

            Long daysBetween = DAYS.between(dateBefore, dateAfter);
            qtd = daysBetween.intValue();



        }catch (Exception ex){
            throw new Exception(ex.getMessage() + ". Formato de data: DD/MM/YYYY");
        }

    	if( qtd <= 0 ){
            throw new Exception("Data Checkin não pode ser maior que a data de Checkout.");
        }

        return qtd;
    }


}
