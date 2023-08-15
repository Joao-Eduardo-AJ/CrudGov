package br.com.syonet.gov;

import java.util.Arrays;
import java.util.List;

import br.com.syonet.gov.entity.City;
import br.com.syonet.gov.entity.Region;
import br.com.syonet.gov.entity.State;
import br.com.syonet.gov.persistence.Persistence;

public class Application {
	public static void main(String[] args) {
		Persistence persistence = new Persistence();

		State state = new State();
		state.setName("Rio Grande do Sul");
		persistence.saveState(state);

		City city1 = new City();
		City city2 = new City();
		City city3 = new City();
		City city4 = new City();
		City city5 = new City();

		city1.setName("Canoas");
		city2.setName("Montenegro");
		city3.setName("Nova Santa Rita");
		city4.setName("São Leopoldo");
		city5.setName("Porto Alegre");

		Region region1 = new Region();
		Region region2 = new Region();
		Region region3 = new Region();
		Region region4 = new Region();
		Region region5 = new Region();

		region1.setName("Setor 1");
		region2.setName("Setor 2");
		region3.setName("Setor 3");
		region4.setName("Setor 4");
		region5.setName("Setor 5");

		List<City> cities = Arrays.asList(city1, city2, city3, city4, city5);
		List<Region> regions = Arrays.asList(region1, region2, region3, region4, region5);

		for (City city : cities) {
			city.setState(state);
			persistence.saveCity(city);
		}

		for (Region region : regions) {
			for (City city : cities) {
				if (region.getCity() != null) {
					region.setCity(city);
				}
			}
			persistence.saveRegion(region);
		}
	}
}
