package alogrithms.search;

public abstract class AState {

    protected Object m_state;
    protected AState m_prev;
    protected boolean m_isDiscoverd;
    protected double m_coast;


    protected AState(){}
    /**
     * constructor for a state
     * @param o
     */
    public AState (Object o){

        if (o != null){
            m_state = o;
            m_prev = null;
            m_coast = -1;
            m_isDiscoverd = false;
        }
    }

    /**
     * constructor for a state
     * @param o
     */
    public AState (Object o, AState prev , double coast){

        if (o != null && prev != null){
            m_state = o;
            m_prev = prev;
            m_coast = coast;
            m_isDiscoverd = false;
        }
    }

    /**
     * constructor for a weight state
     * @param o
     * @param coast
     */
    public AState (Object o, double coast){

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
