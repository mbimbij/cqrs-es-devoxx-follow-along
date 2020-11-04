package com.example.demo.externalprojections;

import com.example.demo.INotifiyDomainEvent;

public interface Projection {
  void apply(INotifiyDomainEvent event);
}
