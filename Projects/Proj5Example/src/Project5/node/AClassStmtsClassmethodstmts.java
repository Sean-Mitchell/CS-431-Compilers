/* This file was generated by SableCC (http://www.sablecc.org/). */

package Project5.node;

import Project5.analysis.*;

@SuppressWarnings("nls")
public final class AClassStmtsClassmethodstmts extends PClassmethodstmts
{
    private PClassmethodstmts _classmethodstmts_;
    private PClassmethodstmt _classmethodstmt_;

    public AClassStmtsClassmethodstmts()
    {
        // Constructor
    }

    public AClassStmtsClassmethodstmts(
        @SuppressWarnings("hiding") PClassmethodstmts _classmethodstmts_,
        @SuppressWarnings("hiding") PClassmethodstmt _classmethodstmt_)
    {
        // Constructor
        setClassmethodstmts(_classmethodstmts_);

        setClassmethodstmt(_classmethodstmt_);

    }

    @Override
    public Object clone()
    {
        return new AClassStmtsClassmethodstmts(
            cloneNode(this._classmethodstmts_),
            cloneNode(this._classmethodstmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClassStmtsClassmethodstmts(this);
    }

    public PClassmethodstmts getClassmethodstmts()
    {
        return this._classmethodstmts_;
    }

    public void setClassmethodstmts(PClassmethodstmts node)
    {
        if(this._classmethodstmts_ != null)
        {
            this._classmethodstmts_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classmethodstmts_ = node;
    }

    public PClassmethodstmt getClassmethodstmt()
    {
        return this._classmethodstmt_;
    }

    public void setClassmethodstmt(PClassmethodstmt node)
    {
        if(this._classmethodstmt_ != null)
        {
            this._classmethodstmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classmethodstmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._classmethodstmts_)
            + toString(this._classmethodstmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._classmethodstmts_ == child)
        {
            this._classmethodstmts_ = null;
            return;
        }

        if(this._classmethodstmt_ == child)
        {
            this._classmethodstmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._classmethodstmts_ == oldChild)
        {
            setClassmethodstmts((PClassmethodstmts) newChild);
            return;
        }

        if(this._classmethodstmt_ == oldChild)
        {
            setClassmethodstmt((PClassmethodstmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
