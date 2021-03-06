/* This file was generated by SableCC (http://www.sablecc.org/). */

package Project5.node;

import Project5.analysis.*;

@SuppressWarnings("nls")
public final class ABooleanBoolid extends PBoolid
{
    private PBoolean _boolean_;

    public ABooleanBoolid()
    {
        // Constructor
    }

    public ABooleanBoolid(
        @SuppressWarnings("hiding") PBoolean _boolean_)
    {
        // Constructor
        setBoolean(_boolean_);

    }

    @Override
    public Object clone()
    {
        return new ABooleanBoolid(
            cloneNode(this._boolean_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABooleanBoolid(this);
    }

    public PBoolean getBoolean()
    {
        return this._boolean_;
    }

    public void setBoolean(PBoolean node)
    {
        if(this._boolean_ != null)
        {
            this._boolean_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._boolean_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._boolean_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._boolean_ == child)
        {
            this._boolean_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._boolean_ == oldChild)
        {
            setBoolean((PBoolean) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
