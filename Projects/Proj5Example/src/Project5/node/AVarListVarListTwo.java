/* This file was generated by SableCC (http://www.sablecc.org/). */

package Project5.node;

import Project5.analysis.*;

@SuppressWarnings("nls")
public final class AVarListVarListTwo extends PVarListTwo
{
    private PExprOrBool _exprOrBool_;
    private PMoreVarListTwo _moreVarListTwo_;

    public AVarListVarListTwo()
    {
        // Constructor
    }

    public AVarListVarListTwo(
        @SuppressWarnings("hiding") PExprOrBool _exprOrBool_,
        @SuppressWarnings("hiding") PMoreVarListTwo _moreVarListTwo_)
    {
        // Constructor
        setExprOrBool(_exprOrBool_);

        setMoreVarListTwo(_moreVarListTwo_);

    }

    @Override
    public Object clone()
    {
        return new AVarListVarListTwo(
            cloneNode(this._exprOrBool_),
            cloneNode(this._moreVarListTwo_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarListVarListTwo(this);
    }

    public PExprOrBool getExprOrBool()
    {
        return this._exprOrBool_;
    }

    public void setExprOrBool(PExprOrBool node)
    {
        if(this._exprOrBool_ != null)
        {
            this._exprOrBool_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprOrBool_ = node;
    }

    public PMoreVarListTwo getMoreVarListTwo()
    {
        return this._moreVarListTwo_;
    }

    public void setMoreVarListTwo(PMoreVarListTwo node)
    {
        if(this._moreVarListTwo_ != null)
        {
            this._moreVarListTwo_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._moreVarListTwo_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exprOrBool_)
            + toString(this._moreVarListTwo_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exprOrBool_ == child)
        {
            this._exprOrBool_ = null;
            return;
        }

        if(this._moreVarListTwo_ == child)
        {
            this._moreVarListTwo_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exprOrBool_ == oldChild)
        {
            setExprOrBool((PExprOrBool) newChild);
            return;
        }

        if(this._moreVarListTwo_ == oldChild)
        {
            setMoreVarListTwo((PMoreVarListTwo) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
