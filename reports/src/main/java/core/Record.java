package core;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
     * number of the protocol.
     */
    private String number;
    /**
     * request date.
     */
    private Calendar date;
    /**
     * specialist name.
     */
    private String specName;
    /**
     * object title.
     */
    private String title;
    /**
     * object address.
     */
    private String address;
    /**
     * harm factor.
     */
    private Set<Protocol> protocols;
    /**
     * object type.
     */
    private String type;
    /**
     * district of object.
     */
    private String district;
    /**
     * reason document.
     */
    private String reason;
    /**
     * number and date of reason document.
     */
    private String reasonAttributes;
    /**
     * planned/not planned/commercial.
     */
    private Purpose purpose;

    /**
     * default constructor.
     */
    public Record() {
        this.protocols = new HashSet<>();
    }

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
    public String getSpecName() {
        return specName;
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
     * getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * getter for number.
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * getter for title.
     * @return title of an object
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for protocols.
     * @return list of protocols
     */
    public Set<Protocol> getProtocols() {
        return protocols;
    }

    /**
     * getter for district.
     * @return object district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * setter for protocol number.
     * @param newNumber - protocol number
     */
    public void setNumber(final String newNumber) {
        this.number = newNumber;
    }

    /**
     * setter for object title.
     * @param newTitle - object title
     */
    public void setTitle(final String newTitle) {
        this.title = newTitle;
    }

    /**
     * setter for object district.
     * @param newDistrict - object district
     */
    public void setDistrict(final String newDistrict) {
        this.district = newDistrict;
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
    public void setSpecName(final String newName) {
        this.specName = newName;
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
     * setter for type.
     * @param newType - new type
     */
    public void setType(final String newType) {
        this.type = newType;
    }

    /**
     * getter for reason document.
     * @return reason document type
     */
    public String getReason() {
        return reason;
    }

    /**
     * setter for reason document.
     * @param newReason - new reason
     */
    public void setReason(final String newReason) {
        this.reason = newReason;
    }

    /**
     * getter for reason document attributes.
     * @return attributes
     */
    public String getReasonAttributes() {
        return reasonAttributes;
    }

    /**
     * setter for reason attributes.
     * @param newReasonAttributes - reason attributes
     */
    public void setReasonAttributes(final String newReasonAttributes) {
        this.reasonAttributes = newReasonAttributes;
    }

    /**
     * setter for purpose.
     * @param newPurpose - purpose
     */
    public void setPurpose(final Purpose newPurpose) {
        this.purpose = newPurpose;
    }

    /**
     * adds new protocol to protocols set.
     * @param newPlaceType - place type
     * @param newFactor - harm factor
     * @param newPointCount - count of points
     * @param newIncPointCount - count of inconsistent points
     * @return success / failure
     */
    public boolean addProtocol(final String newPlaceType,
                               final String newFactor,
                               final int newPointCount,
                               final int newIncPointCount) {
        protocols.add(new Protocol(newPlaceType, newFactor, newPointCount,
                newIncPointCount));
        return true;
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
        return Objects.equals(id, record.id)
                && Objects.equals(number, record.number)
                && Objects.equals(date, record.date)
                && Objects.equals(specName, record.specName)
                && Objects.equals(title, record.title)
                && Objects.equals(address, record.address)
                && Objects.equals(protocols, record.protocols)
                && Objects.equals(type, record.type)
                && Objects.equals(district, record.district)
                && Objects.equals(reason, record.reason)
                && Objects.equals(reasonAttributes, record.reasonAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, date, specName, title,
                address, protocols, type, district, reason, reasonAttributes);
    }

    @Override
    public String toString() {
        return "Record{"
                + "id='" + id + '\''
                + ", number='" + number + '\''
                + ", date=" + date
                + ", specName='" + specName + '\''
                + ", title='" + title + '\''
                + ", address='" + address + '\''
                + ", protocols=" + protocols
                + ", type='" + type + '\''
                + ", district='" + district + '\''
                + ", reason='" + reason + '\''
                + ", reasonAttributes='" + reasonAttributes + '\''
                + '}';
    }

    /**
     * data model for measurement protocol.
     * @author mbardakov
     * @since 04.08.2020
     */
    public final class Protocol {
        /**
         * measurement place type.
         */
        private String placeType;
        /**
         * harm factor.
         */
        private String factor;
        /**
         * count of points.
         */
        private int pointCount;
        /**
         * count of inconsistent points.
         */
        private int incPointCount;
        /**
         * main constructor.
         * @param newPlaceType - place type
         * @param newFactor - harm factor
         * @param newPointCount - count of points
         * @param newIncPointCount - count of inconsistent points
         */
        public Protocol(final String newPlaceType, final String newFactor,
                        final int newPointCount, final int newIncPointCount) {
            this.placeType = newPlaceType;
            this.factor = newFactor;
            this.pointCount = newPointCount;
            this.incPointCount = newIncPointCount;
        }

        /**
         * getter for place type.
         * @return place type
         */
        public String getPlaceType() {
            return placeType;
        }

        /**
         * getter for harm factor.
         * @return harm factor
         */
        public String getFactor() {
            return factor;
        }

        /**
         * getter for point count.
         * @return point count
         */
        public int getPointCount() {
            return pointCount;
        }

        /**
         * getter for inconsistent point count.
         * @return inconsistent point count
         */
        public int getIncPointCount() {
            return incPointCount;
        }

        /**
         * getter for purpose.
         * @return purpose
         */
        public Purpose getPurpose() {
            return purpose;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Protocol protocol = (Protocol) o;
            return pointCount == protocol.pointCount
                    && incPointCount == protocol.incPointCount
                    && Objects.equals(placeType, protocol.placeType)
                    && Objects.equals(factor, protocol.factor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(placeType, factor, pointCount, incPointCount);
        }

        @Override
        public String toString() {
            return "Protocol{"
                    + "placeType='" + placeType + '\''
                    + ", factor='" + factor + '\''
                    + ", pointCount=" + pointCount
                    + ", incPointCount=" + incPointCount
                    + ", purpose=" + purpose
                    + '}';
        }
    }
    public enum Purpose {
        /**
         * state planned.
         */
        PLANNED,
        /**
         *state not planned.
         */
        NOTPLANNED,
        /**
         * commercial purpose.
         */
        COMMERCIAL,
        /**
         * other purposes.
         */
        OTHER
    }
}
