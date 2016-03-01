package com.epam.chadov.task1.model;



/**
 *
 */

public abstract class AbstractEntity {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = Integer.valueOf(id.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id ^ (id >>> 32);
    }
}
