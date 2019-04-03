package alogrithms.search;

public abstract class AState {

    private Object m_state;
    private AState m_prev;
    private boolean m_isDiscoverd;
    private double m_coast;

    /**
     * constructor for a state
     * @param o
     */
    AState (Object o){

        if (o != null){
            m_state = o;
            m_prev = null;
            m_coast = -1;
            m_isDiscoverd = false;
        }
    }

    /**
     * constructor for a weight state
     * @param o
     * @param coast
     */
    AState (Object o, double coast){

        if (o != null){
            m_state = o;
            m_prev = null;
            m_coast = coast;
            m_isDiscoverd = false;
        }
    }

    /**
     * returns the previous state
     * @return
     */
    public AState getPrev() {
        return m_prev;
    }


    /**
     * set new previous state
     * @param prev
     */
    public void setPrev(AState prev) {
        m_prev = prev;
    }

    public void setM_isDiscoverd(boolean m_isDiscoverd) {
        this.m_isDiscoverd = m_isDiscoverd;
    }

    public boolean getM_isDiscoverd() {
        return m_isDiscoverd;
    }


    /**
     * compare between two states
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null)
            return false;

        return m_state.equals(obj);
    }
}
