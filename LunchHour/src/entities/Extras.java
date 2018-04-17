package entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public  class Extras {
private String ExtraName;
private Double ExtraPrice;
private final SimpleIntegerProperty itemMaxCount = new SimpleIntegerProperty(99);
 private final SimpleIntegerProperty itemCount = new SimpleIntegerProperty(0);
 public String getExtraName() {
		return ExtraName;
	}
	public void setExtraName(String extraName) {
		ExtraName = extraName;
	}

	 public void setExtraPrice(Double extraPrice) {
		ExtraPrice = extraPrice;
	}

	public Double getExtraPrice() {
		return ExtraPrice;
	}
    @Override
    public String toString() {
        return Integer.toString(getItemCount());
    }

    public final int getItemCount() {
        return this.itemCount.get();
    }

    public final void setItemCount(int value) {
        this.itemCount.set(value);
    }

    public final IntegerProperty itemCountProperty() {
        return this.itemCount;
    }

}