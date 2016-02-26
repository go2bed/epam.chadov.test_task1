package com.epam.chadov.task1.model;

/**
 *
 */

public abstract class AbstractEntity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = (Long)id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id == that.id;

    }

//    @Override
//    public int hashCode() {
//        return (Long) (id ^ (id >>> 32));
//    }
}
