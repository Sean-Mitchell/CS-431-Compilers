/* This file was generated by SableCC (http://www.sablecc.org/). */

package Project5.node;

import Project5.analysis.*;

@SuppressWarnings("nls")
public final class TThen extends Token
{
    public TThen()
    {
        super.setText("THEN");
    }

    public TThen(int line, int pos)
    {
        super.setText("THEN");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TThen(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTThen(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TThen text.");
    }
}
