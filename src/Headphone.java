
public class Headphone extends Item {

	private boolean bluetooth;
	private boolean noise_cancel;
	
	public Headphone(String _itemId, String _name, int _height, int _width, Vendor _vendor, boolean _bluetooth, boolean _noise_cancel) {
		super(_itemId, _name, _height, _width, _vendor);
		bluetooth = _bluetooth;
		noise_cancel = _noise_cancel;
	}
	
	public void setBluetooth(boolean _bluetooth) {
		bluetooth = _bluetooth;
	}
	
	public void setNoise_cancel(boolean _noise_cancel) {
		noise_cancel = _noise_cancel;
	}
	
	public boolean hasBluetooth() {
		return bluetooth;
	}
	
	public boolean hasNoise_cancel() {
		return noise_cancel;
	}

}
