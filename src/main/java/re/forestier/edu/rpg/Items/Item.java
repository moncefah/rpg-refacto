package re.forestier.edu.rpg.Items;

public class Item {
    private String name;
    private String description;
    private int weight;
    private int value;

    public Item(String name, String description, int weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name)
      .append(" (")
      .append(description)
      .append(", ")
      .append(weight)
      .append("kg, ")
      .append(value)
      .append("$)");
    return sb.toString();
  }

}