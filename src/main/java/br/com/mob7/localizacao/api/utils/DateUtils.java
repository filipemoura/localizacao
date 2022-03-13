package br.com.mob7.localizacao.api.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtils {
	
	private static final int RAIO_DA_TERRA = 6371;
	private static final int KM_PARA_METROS = 1000;
	
	public static Long intervaloEmMinutos(LocalDateTime date1, LocalDateTime date2) {
		return Duration.between(date1, date2).toSeconds();
	}
	
	public static String intervaloEmHorasMinutosSegundos(Long segundosParam) {
		Duration duracao =  Duration.ofSeconds(segundosParam);
        long horas = duracao.toHours();
        long minutos = duracao.minusHours(horas).toMinutes();
        long segundos = duracao.minusHours(horas).minusMinutes(minutos).getSeconds();
        
        return horas+ " hora(s) : " +minutos+  " minuto(s) : " +segundos+ " segundo(s) ";
	}
	
	/**
	 * 
	 * @param lat1 = Latitude ponto 1 	//base
	 * @param lon1 = Longitude ponto 1  //base
	 * @param lat2 = Latitude ponto 2   //posicao
	 * @param lon2 = Longitude ponto 2  //posicao
	 * 
	 * @return Distância entre dois pontos de uma esfera a partir de suas latitudes e longitudes.
	 */
	public static Double formulaHaversine(Double lat1, Double lon1, Double lat2, Double lon2) {
		Double latDistancia = toRadians(lat2-lat1);
		Double lonDistancia = toRadians(lon2-lon1);
		
		Double acos = Math.sin(latDistancia / 2) * 
					Math.sin(latDistancia / 2) + 
					Math.cos(toRadians(lat1)) * 
					Math.cos(toRadians(lat2)) * 
					Math.sin(lonDistancia / 2) * 
					Math.sin(lonDistancia / 2);
		
		Double resultado = 2 * Math.atan2(Math.sqrt(acos), Math.sqrt(1-acos));
		
		return (RAIO_DA_TERRA * resultado) * KM_PARA_METROS;
	}
	
	private static Double toRadians(Double value) {
		return value * Math.PI / 180;
	}

}
