package com.br.cvc.client;



import com.br.cvc.dto.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class BrokerClient {

    private static final Logger LOG = Logger.getLogger(BrokerClient.class.getName());

    private static final String BROKER_API_FIND_CIDADE_URL = "https://cvcbackendhotel.herokuapp.com/hotels/avail/";

    private static final String BROKER_API_FIND_HOTEL_URL = "https://cvcbackendhotel.herokuapp.com/hotels/";


    public Hotel[] findHotelByCity(int id_cidade) throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(BROKER_API_FIND_CIDADE_URL+"%s", id_cidade);

        try {
            LOG.info("Accessing the external broker api: Getting hotels by city code: " + id_cidade);

            Hotel[] results = restTemplate.getForObject(url, Hotel[].class);
            return results;
        } catch(Exception ex) {
            LOG.severe("Error could not access the api: " + ex.getMessage());
            throw new Exception(ex);
        }
    }


    public Hotel findHotel(int id_hotel) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(BROKER_API_FIND_HOTEL_URL+"%s", id_hotel);
        try {
            LOG.info("Accessing the external broker api: Getting hotels by hotel code:" + id_hotel);
            Hotel[] results = restTemplate.getForObject(url, Hotel[].class);
            return results[0];
        } catch(Exception ex) {
            LOG.severe("Error could not access the api:" + ex.getMessage());
            throw new Exception(ex);
        }
    }
}
