/* This file was generated by SableCC (http://www.sablecc.org/). */

package Project5.node;

import Project5.analysis.*;

@SuppressWarnings("nls")
public final class TMultop extends Token
{
    public TMultop(String text)
    {
        setText(text);
    }

    public TMultop(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMultop(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMultop(this);
    }
}
