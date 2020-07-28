package core;

import java.util.Calendar;
import java.util.Objects;

/** data model class dor memory store.
 * @author mbardakov
 * @since 28.07.2020
 */
public final class Record {
    /**
     * record id.
     */
    private String id;
    /**
     * object title.
     */
    private String name;
    /**
     * object address.
     */
    private String address;
    /**
     * measurement date.
     */
    private Calendar date;
    /**
     * harm factor.
     */
    private String factor;
    /**
     * object type.
     */
    private String type;
    /**
     * count of measured points.
     */
    private int pointCount;
    /**
     * count of inconsistent points.
     */
    private int incPointCount;
    /**
     * id getter.
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * name getter.
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * getter for address.
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * getter for date.
     * @return date
     */
    public Calendar getDate() {
        return date;
    }
    /**
     * getter for factor.
     * @return factor
     */
    public String getFactor() {
        return factor;
    }
    /**
     * getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }
    /**
     * getter for pointCount.
     * @return pointCount.
     */
    public int getPointCount() {
        return pointCount;
    }

    /**
     * getter for incPointCount.
     * @return incPointCount
     */
    public int getIncPointCount() {
        return incPointCount;
    }

    /**
     * setter for id.
     * @param newId - new id
     */
    public void setId(final String newId) {
        this.id = newId;
    }

    /**
     * setter for name.
     * @param newName - new name
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * setter for address.
     * @param newAddress - newAddress
     */
    public void setAddress(final String newAddress) {
        this.address = newAddress;
    }

    /**
     * setter for date.
     * @param newDate - new date
     */
    public void setDate(final Calendar newDate) {
        this.date = newDate;
    }

    /**
     * setter for factor.
     * @param newFactor - new factor;
     */
    public void setFactor(final String newFactor) {
        this.factor = newFactor;
    }

    /**
     * setter for type.
     * @param newType - new type
     */
    public void setType(final String newType) {
        this.type = newType;
    }

    /**
     * setter for pointCount.
     * @param newPointCount - new pointCount
     */
    public void setPointCount(final int newPointCount) {
        this.pointCount = newPointCount;
    }

    /**
     * setter for incPointCount.
     * @param newIncPointCount - new incPointCount
     */
    public void setIncPointCount(final int newIncPointCount) {
        this.incPointCount = newIncPointCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return pointCount == record.pointCount
                && incPointCount == record.incPointCount
                && Objects.equals(id, record.id)
                && Objects.equals(name, record.name)
                && Objects.equals(address, record.address)
                && Objects.equals(date, record.date)
                && Objects.equals(factor, record.factor)
                && Objects.equals(type, record.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, name, address, date, factor, type, pointCount, incPointCount
        );
    }
}
