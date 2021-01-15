package entities;

import java.util.List;

public interface Strategy {
    public List<Producer> apply();
}
