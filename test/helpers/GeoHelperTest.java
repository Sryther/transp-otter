package helpers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ynov.transpotter.exceptions.NegativeRadiusException;
import com.ynov.transpotter.helpers.GeoHelper;
import com.ynov.transpotter.models.Coordinates;

public class GeoHelperTest {
	private final Coordinates paris = new Coordinates(48.856614, 2.3522219000000177);
	private final Coordinates perpignan = new Coordinates(42.6886591, 2.8948331999999937);
	private final int wrongRadius = 100;
	private final int coolRadius = 700;
	private final int negativeRadius = -1;

	@Test
	public void testFalse() {
		try {
			assertFalse(GeoHelper.isInRadius(paris, wrongRadius, perpignan));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTrue() {
		try {
			assertTrue(GeoHelper.isInRadius(paris, coolRadius, perpignan));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mustFail() {
		try {
			GeoHelper.isInRadius(paris, negativeRadius, perpignan);
		} catch (Exception e) {
			assert(e instanceof NegativeRadiusException);
		}
	}
}
