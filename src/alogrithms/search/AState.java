package alogrithms.search;

public abstract class AState {

    Object m_state;
    AState m_prev;
    double m_coast;
    double m_pathCoast;

    /**
     * AState default Constructor
     */
    protected AState(){}

    /**
     * AState Constructor
     * @param o - problem state
     */
    public AState (Object o){

        if (o != null){
            m_state = o;
            m_prev = null;
            m_coast = -1;
        }
    }

    /**
     * AState Constructor - that gets a curtain problem state
     * a previous state and a coast to get to get to that curtain state
     * @param o - problem state
     * @param prev - previous state
     * @param coast - coast to the current state
     */
    public AState(Object o, AState prev , double coast){

        if (o != null && prev != null){
            m_state = o;
            m_prev = prev;
            m_coast = coast;
        }
    }

    /**
     * AState Constructor - that gets a curtain problem state
     * a previous state and a coast to get to get to that curtain state
     * @param o - problem state
     * @param coast - coast to the current state
     */
    public AState (Object o, double coast){

        if (o != null){
            m_state = o;
            m_prev = null;
            m_coast = coast;
        }
    }

    /**
     * Returns the previous state
     * @return previous state
     */
    AState getPrev() {
        return m_prev;
    }

    /**
     * Sets new previous state
     * @param prev - previous state
     */
    void setPrev(AState prev) {
        m_prev = prev;
    }

    /**
     * Getter - Returns the current state
     * @return The current state
     */
    public Object getM_state(){ return m_state; }

    /**
     * Setter - Sets a States coast
     * @param coast - States coast
     */
    void setM_pathCoast(double coast){
        m_pathCoast = coast;
    }

    /**
     * Getter - Returns the state coast
     * @return The state coast
     */
    double getM_pathCoast() {
        return m_pathCoast;
    }

    /**
     * @param m_coast - The state coast
     */
    void setM_coast(double m_coast) {
        this.m_coast = m_coast;
    }

    /**
     * Compare between two states
     * @param other - State to compare to
     * @return if this and
     */
    @Override
    public boolean equals(Object other) {

        if (other == null || !(other instanceof AState))
            return false;

        return m_state.equals(((AState)other).m_state);
    }

    /**
     * Returns the current state toString
     * @return string
     */
    @Override
    public String toString() {
        return this.m_state.toString();
    }
}
