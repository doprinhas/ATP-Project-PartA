package alogrithms.search;

public abstract class AState {

    protected Object m_state;
    protected AState m_prev;
    protected double m_coast;
    protected double m_pathCoast;

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

    public Object getM_state(){ return m_state; }

    public void setM_pathCoast(double coast){
        m_pathCoast = coast;
    }

    public double getM_pathCoast() {
        return m_pathCoast;
    }

    public double getM_coast() {
        return m_coast;
    }

    public void setM_coast(double m_coast) {
        this.m_coast = m_coast;
    }

    /**
     * compare between two states
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof AState))
            return false;

        return m_state.equals(((AState)obj).m_state);
    }

    @Override
    public String toString() {
        return this.m_state.toString();
    }
}
